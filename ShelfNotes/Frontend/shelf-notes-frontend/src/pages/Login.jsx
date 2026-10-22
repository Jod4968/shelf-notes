import { useState } from "react";
import "./Login.css";

function Login() {
const [currentPage, setCurrentPage] = useState("login");


const [email, setEmail] = useState("");
const [password, setPassword] = useState("");
const [confirmPassword, setConfirmPassword] = useState("");

const [showPassword, setShowPassword] = useState(false);
const [showConfirmPassword, setShowConfirmPassword] = useState(false);

const [error, setError] = useState("");
const [message, setMessage] = useState("");

function navigate(page) {
    setCurrentPage(page);
    setError("");
    setMessage("");
    setPassword("");
    setConfirmPassword("");
}

function handleLogin(event) {
    event.preventDefault();

    setError("");
    setMessage("");

    if (!email.trim() || !password) {
        setError("Please enter your email and password.");
        return;
    }

    // Backend authentication will be connected later.
    setMessage("Login form validated successfully!");
}

function handleSignup(event) {
    event.preventDefault();

    setError("");
    setMessage("");

    if (password.length < 8) {
        setError("Password must contain at least 8 characters.");
        return;
    }

    if (password !== confirmPassword) {
        setError("Passwords do not match.");
        return;
    }

    // Backend registration will be connected later.
    setMessage("Signup form validated successfully!");
}

function handleForgotPassword(event) {
    event.preventDefault();

    setError("");
    setMessage(
        "Password reset will be available after connecting the backend."
    );
}

return (
    <div className="auth-page">

        {/* LEFT BRANDING SECTION */}

        <section className="auth-brand">

            <div className="brand-logo">
                <span className="brand-icon">S</span>

                <span>
                    Shelf<span className="brand-accent">Notes</span>
                </span>
            </div>

            <div className="brand-content">

                <span className="brand-tag">
                    YOUR PERSONAL LEARNING SPACE
                </span>

                <h1>
                    Your knowledge,
                    <br />
                    <span>beautifully organized.</span>
                </h1>

                <p>
                    Keep your notes, study materials, and ideas together.
                    Everything you need to learn, all in one place.
                </p>

                <div className="brand-features">

                    <div className="feature-item">
                        <span className="feature-icon">✦</span>

                        <div>
                            <strong>One organized space</strong>
                            <p>
                                Keep your learning resources together.
                            </p>
                        </div>
                    </div>

                    <div className="feature-item">
                        <span className="feature-icon">✓</span>

                        <div>
                            <strong>Built for your learning</strong>
                            <p>
                                Spend less time searching, more time learning.
                            </p>
                        </div>
                    </div>

                </div>

            </div>

            <p className="brand-footer">
                A little progress every day adds up.
            </p>

        </section>


        {/* RIGHT AUTHENTICATION SECTION */}

        <section className="auth-form-side">

            <div className="auth-card">

                <div className="mobile-logo">
                    Shelf<span className="brand-accent">Notes</span>
                </div>


                {/* LOGIN FORM */}

                {currentPage === "login" && (

                    <>

                        <div className="auth-heading">

                            <span className="eyebrow">
                                WELCOME BACK
                            </span>

                            <h2>Sign in to your account</h2>

                            <p>
                                Enter your details to continue.
                            </p>

                        </div>


                        <form onSubmit={handleLogin}>

                            <div className="form-group">

                                <label htmlFor="login-email">
                                    Email address
                                </label>

                                <input
                                    id="login-email"
                                    type="email"
                                    placeholder="you@example.com"
                                    value={email}
                                    onChange={(event) =>
                                        setEmail(event.target.value)
                                    }
                                    autoComplete="email"
                                    required
                                />

                            </div>


                            <div className="form-group">

                                <div className="label-row">

                                    <label htmlFor="login-password">
                                        Password
                                    </label>

                                    <button
                                        type="button"
                                        className="text-button"
                                        onClick={() =>
                                            navigate("forgot")
                                        }
                                    >
                                        Forgot password?
                                    </button>

                                </div>


                                <div className="password-input">

                                    <input
                                        id="login-password"
                                        type={
                                            showPassword
                                                ? "text"
                                                : "password"
                                        }
                                        placeholder="Enter your password"
                                        value={password}
                                        onChange={(event) =>
                                            setPassword(event.target.value)
                                        }
                                        autoComplete="current-password"
                                        required
                                    />

                                    <button
                                        type="button"
                                        className="password-toggle"
                                        onClick={() =>
                                            setShowPassword(!showPassword)
                                        }
                                    >
                                        {showPassword ? "Hide" : "Show"}
                                    </button>

                                </div>

                            </div>


                            {error && (
                                <p className="form-error" role="alert">
                                    {error}
                                </p>
                            )}

                            {message && (
                                <p className="form-success" role="status">
                                    {message}
                                </p>
                            )}


                            <button
                                type="submit"
                                className="primary-button"
                            >
                                Sign In <span>→</span>
                            </button>

                        </form>


                        <div className="auth-switch">

                            Don't have an account?

                            <button
                                type="button"
                                className="text-button"
                                onClick={() => navigate("signup")}
                            >
                                Create account
                            </button>

                        </div>

                    </>

                )}


                {/* SIGNUP FORM */}

                {currentPage === "signup" && (

                    <>

                        <div className="auth-heading">

                            <span className="eyebrow">
                                GET STARTED
                            </span>

                            <h2>Create your account</h2>

                            <p>
                                Join ShelfNotes and organize your learning.
                            </p>

                        </div>


                        <form onSubmit={handleSignup}>

                            <div className="form-group">

                                <label htmlFor="signup-email">
                                    Email address
                                </label>

                                <input
                                    id="signup-email"
                                    type="email"
                                    placeholder="you@example.com"
                                    value={email}
                                    onChange={(event) =>
                                        setEmail(event.target.value)
                                    }
                                    autoComplete="email"
                                    required
                                />

                            </div>


                            <div className="form-group">

                                <label htmlFor="signup-password">
                                    Password
                                </label>

                                <div className="password-input">

                                    <input
                                        id="signup-password"
                                        type={
                                            showPassword
                                                ? "text"
                                                : "password"
                                        }
                                        placeholder="At least 8 characters"
                                        value={password}
                                        onChange={(event) =>
                                            setPassword(event.target.value)
                                        }
                                        autoComplete="new-password"
                                        minLength={8}
                                        required
                                    />

                                    <button
                                        type="button"
                                        className="password-toggle"
                                        onClick={() =>
                                            setShowPassword(!showPassword)
                                        }
                                    >
                                        {showPassword ? "Hide" : "Show"}
                                    </button>

                                </div>

                            </div>


                            <div className="form-group">

                                <label htmlFor="confirm-password">
                                    Confirm password
                                </label>

                                <div className="password-input">

                                    <input
                                        id="confirm-password"
                                        type={
                                            showConfirmPassword
                                                ? "text"
                                                : "password"
                                        }
                                        placeholder="Re-enter your password"
                                        value={confirmPassword}
                                        onChange={(event) =>
                                            setConfirmPassword(
                                                event.target.value
                                            )
                                        }
                                        autoComplete="new-password"
                                        required
                                    />

                                    <button
                                        type="button"
                                        className="password-toggle"
                                        onClick={() =>
                                            setShowConfirmPassword(
                                                !showConfirmPassword
                                            )
                                        }
                                    >
                                        {showConfirmPassword
                                            ? "Hide"
                                            : "Show"}
                                    </button>

                                </div>

                            </div>


                            {error && (
                                <p className="form-error" role="alert">
                                    {error}
                                </p>
                            )}

                            {message && (
                                <p className="form-success" role="status">
                                    {message}
                                </p>
                            )}


                            <button
                                type="submit"
                                className="primary-button"
                            >
                                Create Account <span>→</span>
                            </button>

                        </form>


                        <div className="auth-switch">

                            Already have an account?

                            <button
                                type="button"
                                className="text-button"
                                onClick={() => navigate("login")}
                            >
                                Sign in
                            </button>

                        </div>

                    </>

                )}


                {/* FORGOT PASSWORD FORM */}

                {currentPage === "forgot" && (

                    <>

                        <div className="auth-heading">

                            <span className="eyebrow">
                                ACCOUNT RECOVERY
                            </span>

                            <h2>Forgot your password?</h2>

                            <p>
                                Enter your email address to begin
                                recovering your account.
                            </p>

                        </div>


                        <form onSubmit={handleForgotPassword}>

                            <div className="form-group">

                                <label htmlFor="forgot-email">
                                    Email address
                                </label>

                                <input
                                    id="forgot-email"
                                    type="email"
                                    placeholder="you@example.com"
                                    value={email}
                                    onChange={(event) =>
                                        setEmail(event.target.value)
                                    }
                                    autoComplete="email"
                                    required
                                />

                            </div>


                            {message && (
                                <p className="form-success" role="status">
                                    {message}
                                </p>
                            )}


                            <button
                                type="submit"
                                className="primary-button"
                            >
                                Reset Password <span>→</span>
                            </button>

                        </form>


                        <div className="auth-switch">

                            Remembered your password?

                            <button
                                type="button"
                                className="text-button"
                                onClick={() => navigate("login")}
                            >
                                Back to Sign In
                            </button>

                        </div>

                    </>

                )}


                <p className="form-footer">
                    Your personal space for better learning.
                </p>

            </div>

        </section>

    </div>
);


}

export default Login;
