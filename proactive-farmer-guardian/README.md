# Proactive Farmer Guardian

A decision-support prototype that moves agricultural assistance from a reactive
question-and-answer model to a proactive loop:

> continuous assessment → early warning → explanation → preventive action

The system flags conditions that are known to raise crop risk and always explains *why*
it fired and *what to do next*. It does not claim a diagnosis, and every response it
returns carries that disclaimer.

## Stack

| Layer | Tech |
| --- | --- |
| Frontend | React 19 (Vite), Tailwind CSS v4, Lucide React, Axios — port 5173 |
| Backend | Java 25, Spring Boot 4.1.1 (Spring Web MVC, DevTools) — port 8080 |
| Weather | [Open-Meteo](https://open-meteo.com/) current conditions (free, no API key) |

> Note: the spec called for Spring Boot 3, but Spring Initializr no longer publishes
> 3.x (EOL). This runs on Boot 4.1.1, which is the current supported line and the only
> one compatible with the installed JDK 25. The code shape is unchanged.

## Running it

Two terminals, from the repository root:

```bash
# Terminal 1 - backend on http://localhost:8080
cd backend
./mvnw spring-boot:run

# Terminal 2 - frontend on http://localhost:5173
cd frontend
npm install    # first run only
npm run dev
```

Then open http://localhost:5173. The header shows a live "Backend online" badge, so a
missing backend is obvious before you run a scan.

To point the frontend at a deployed backend, set `VITE_API_BASE_URL`.

## API

### `POST /api/risk/evaluate`

```jsonc
// request
{ "cropType": "Tomato", "growthStage": "Flowering",
  "lat": 18.5204, "lon": 73.8567, "locationLabel": "Pune, Maharashtra" }
```

Fetches live temperature, humidity and precipitation for the coordinates, runs them
through the rule engine, and returns:

```jsonc
{
  "riskLevel": "HIGH",                    // LOW | MEDIUM | HIGH
  "riskTitle": "High Fungal Risk - Late Blight Conditions",
  "contributingFactors": ["…"],           // the "why"
  "preventiveActions": ["…"],             // the "what now"
  "disclaimer": "AI-Assisted Early Warning System - …",
  "cropType": "Tomato",
  "growthStage": "Flowering",
  "locationLabel": "Pune, Maharashtra",
  "weather": { "temperatureC": 22.0, "humidityPercent": 88, "precipitationMm": 0.0,
               "observedAt": "2026-08-26T12:00" },
  "otherRisks": [{ "title": "…", "riskLevel": "MEDIUM" }],
  "evaluatedAt": "2026-08-26T12:21:22Z"
}
```

`riskLevel` and the severity word in `riskTitle` are derived from the same value, so
they can never disagree. When several rules fire, the most severe becomes the headline
and the rest are returned in `otherRisks` rather than being dropped.

### `POST /api/crop/analyze`

`multipart/form-data` with an `image` part. **Mocked for this MVP** — it validates the
upload, then returns one of a fixed set of plausible conditions at 85% confidence. The
response includes `modelStatus: "MOCK - …"` so the UI can state plainly that no vision
model is running yet.

### `GET /api/health`

Liveness probe used by the dashboard's status badge.

Errors on every endpoint come back as
`{ "status": 400, "message": "human readable", "timestamp": "…" }`.

## Risk rules

Hardcoded and transparent — no trained model. Each rule pairs a threshold with both its
evidence and its remedy. Crop-specific rules run first, then crop-agnostic weather rules,
so an unrecognised crop still gets heat/rain/moisture warnings.

| Crop | Condition | Signal |
| --- | --- | --- |
| Tomato, Potato | humidity > 75% and 10–28 °C | Late blight (HIGH at ≥85% RH, when raining, or at flowering/fruiting) |
| Grape | humidity ≥ 70% | Downy mildew (HIGH at ≥80% RH) |
| Onion | humidity ≥ 75% and ≥ 20 °C | Purple blotch |
| Wheat | humidity ≥ 70% and 12–24 °C | Rust-favourable weather |
| Cotton | 25–36 °C and 45–75% RH | Sucking pest build-up |
| Sugarcane | ≥ 33 °C and < 45% RH | Water stress |
| *any* | ≥ 38 °C | Heat stress (HIGH at flowering/fruiting) |
| *any* | ≥ 5 mm rain | Waterlogging and spray wash-off (≥ 1 mm → leaf wetness) |
| *any* | < 30% RH and ≥ 32 °C | Moisture deficit |

Because the rules run on live weather, the demo location matters: pick a humid location
(Nashik, Kolhapur, Nagpur) to see a red card and a dry one (Pune, Solapur) to see green.

## Tests

```bash
cd backend && ./mvnw test
```

`RiskEngineTest` pins the branches live weather cannot be relied on to produce on demand
— heavy rain, extreme heat, several rules firing at once — plus the invariant that the
title never contradicts `riskLevel`.

## Scope and honesty

This is a prototype, not a product. Known limits, stated deliberately rather than hidden:

- Leaf-image analysis is mocked; there is no vision model.
- The assessment log lives in browser memory and is lost on refresh.
- Rules use a single current-conditions reading, not a multi-day forecast or the
  leaf-wetness duration a real disease model would need.
