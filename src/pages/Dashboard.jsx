import { useState } from "react";
import "./Dashboard.css";

function Dashboard() {
const [activePage, setActivePage] = useState("Dashboard");


const menuItems = [
    { name: "Dashboard", icon: "⌂" },
    { name: "My Notes", icon: "▤" },
    { name: "Favorites", icon: "☆" },
    { name: "Categories", icon: "▦" },
    { name: "Trash", icon: "♲" },
];

const notes = [
    {
        id: 1,
        title: "Java & DSA",
        description: "Important concepts, algorithms, and problem-solving notes.",
        category: "Programming",
        color: "lavender",
        updated: "Recently updated",
    },
    {
        id: 2,
        title: "Database Management",
        description: "SQL queries, normalization, keys, and database concepts.",
        category: "Computer Science",
        color: "mint",
        updated: "Recently updated",
    },
    {
        id: 3,
        title: "React Fundamentals",
        description: "Components, props, state, and hooks.",
        category: "Web Development",
        color: "peach",
        updated: "Recently updated",
    },
];

return (
    <div className="dashboard-layout">

        {/* SIDEBAR */}

        <aside className="dashboard-sidebar">

            <div className="dashboard-logo">
                <span className="dashboard-logo-icon">S</span>

                <span>
                    Shelf<span>Notes</span>
                </span>
            </div>

            <p className="sidebar-label">WORKSPACE</p>

            <nav className="sidebar-navigation">
                {menuItems.map((item) => (
                    <button
                        key={item.name}
                        className={`sidebar-item ${
                            activePage === item.name ? "active" : ""
                        }`}
                        onClick={() => setActivePage(item.name)}
                    >
                        <span className="sidebar-icon">{item.icon}</span>
                        <span>{item.name}</span>
                    </button>
                ))}
            </nav>

            <div className="sidebar-bottom">

                <div className="sidebar-tip">
                    <span className="tip-icon">✦</span>
                    <h4>Keep learning</h4>
                    <p>Consistent progress today builds lasting knowledge.</p>
                </div>

                <button
                    className="sidebar-item"
                    onClick={() => alert("Settings are coming soon.")}
                >
                    <span className="sidebar-icon">⚙</span>
                    <span>Settings</span>
                </button>

                <button
                    className="sidebar-item logout-item"
                    onClick={() => alert("Logout will be available soon.")}
                >
                    <span className="sidebar-icon">↪</span>
                    <span>Logout</span>
                </button>

            </div>

        </aside>


        {/* MAIN CONTENT */}

        <main className="dashboard-main">

            {/* TOP BAR */}

            <header className="dashboard-topbar">

                <div className="breadcrumb">
                    Workspace <span>/</span> {activePage}
                </div>

                <div className="topbar-right">

                    <div className="profile-avatar">S</div>

                    <div className="profile-details">
                        <strong>Student</strong>
                        <span>My workspace</span>
                    </div>

                </div>

            </header>


            {/* PAGE CONTENT */}

            <div className="dashboard-content">

                <section className="welcome-section">

                    <div>
                        <p className="welcome-eyebrow">
                            YOUR PERSONAL KNOWLEDGE WORKSPACE
                        </p>

                        <h1>
                            Welcome back <span>✦</span>
                        </h1>

                        <p className="welcome-description">
                            Keep your knowledge organized and accessible.
                            What would you like to learn today?
                        </p>
                    </div>

                    <button
                        className="create-note-button"
                        onClick={() => alert("The note creation form is coming soon.")}
                    >
                        <span>＋</span> Create Note
                    </button>

                </section>


                {/* STATISTICS */}

                <section className="statistics-grid">

                    <div className="stat-card">
                        <div className="stat-icon purple-icon">▤</div>

                        <div>
                            <p>Total Notes</p>
                            <h2>—</h2>
                            <span>Ready to track your progress</span>
                        </div>
                    </div>

                    <div className="stat-card">
                        <div className="stat-icon pink-icon">☆</div>

                        <div>
                            <p>Favorites</p>
                            <h2>—</h2>
                            <span>Your saved resources</span>
                        </div>
                    </div>

                    <div className="stat-card">
                        <div className="stat-icon blue-icon">◷</div>

                        <div>
                            <p>Recently Updated</p>
                            <h2>—</h2>
                            <span>Your most recent activity</span>
                        </div>
                    </div>

                </section>


                {/* NOTES SECTION */}

                <section className="notes-section">

                    <div className="section-heading">

                        <div>
                            <h2>
                                {activePage === "Dashboard"
                                    ? "Recent Notes"
                                    : activePage}
                            </h2>

                            <p>
                                Keep your learning materials organized in one place.
                            </p>
                        </div>

                        <button
                            className="view-all-button"
                            onClick={() => setActivePage("My Notes")}
                        >
                            View all <span>→</span>
                        </button>

                    </div>


                    <div className="notes-grid">

                        {notes.map((note) => (
                            <article className="note-card" key={note.id}>

                                <div className="note-card-top">

                                    <span className={`note-category ${note.color}`}>
                                        {note.category}
                                    </span>

                                    <button
                                        className="note-menu-button"
                                        aria-label={`More options for ${note.title}`}
                                        onClick={() => alert("Note actions are coming soon.")}
                                    >
                                        ⋯
                                    </button>

                                </div>

                                <div className={`note-illustration ${note.color}`}>
                                    <span>
                                        {note.id === 1
                                            ? "</>"
                                            : note.id === 2
                                            ? "▤"
                                            : "✳"}
                                    </span>
                                </div>

                                <h3>{note.title}</h3>

                                <p className="note-description">
                                    {note.description}
                                </p>

                                <div className="note-card-footer">
                                    <span className="note-updated">
                                        ◷ {note.updated}
                                    </span>

                                    <button
                                        className="open-note-button"
                                        onClick={() => alert("Note viewing is coming soon.")}
                                    >
                                        →
                                    </button>
                                </div>

                            </article>
                        ))}

                    </div>

                </section>


                {/* BOTTOM BANNER */}

                <section className="dashboard-banner">

                    <div>
                        <span className="banner-label">A LITTLE INSPIRATION</span>

                        <h3>Every expert starts as a beginner.</h3>

                        <p>
                            Keep building your knowledge. Your future self will thank you.
                        </p>
                    </div>

                    <div className="banner-decoration">✦</div>

                </section>

                <footer className="dashboard-footer">
                    Designed for curious minds. <span>♥</span> ShelfNotes
                </footer>

            </div>

        </main>

    </div>
);


}

export default Dashboard;
