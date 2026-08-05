# Shelf Notes

## 1. Project Overview

### 1.1 Introduction

Shelf Notes is a web application designed to help users organize, manage, and store study resources in one centralized platform. Users can save study materials such as PDF files, images, documents, and YouTube links, making them easily accessible whenever needed.

Instead of keeping study resources scattered across multiple applications like Google Drive, WhatsApp, Telegram, Downloads, and browser bookmarks, Shelf Notes provides a single organized workspace where everything is stored category-wise.

---

### 1.2 Target Users

Shelf Notes is primarily designed for:

* Students
* Teachers
* Self-learners
* Anyone who wants to organize educational resources

---

### 1.3 Problem Statement

Students and learners often store study materials in multiple locations, making them difficult to find later. PDFs may be stored in Downloads, lecture links in browser bookmarks, handwritten notes as images in the gallery, and documents across different cloud storage services.

This scattered organization wastes time and reduces productivity.

Shelf Notes solves this problem by providing one centralized platform where all study resources can be stored, categorized, searched, and accessed efficiently.

---

### 1.4 Objectives

The primary objectives of Shelf Notes are:

* Organize study materials in one place.
* Allow users to create categories for better organization.
* Store PDFs, images, documents, and YouTube links.
* Enable quick searching and filtering of study materials.
* Allow users to bookmark important resources.
* Give users the option to keep resources private or make them public.
* Provide a dashboard with useful statistics and study activity.

---

### 1.5 Why Shelf Notes?

Unlike traditional folders or cloud storage, Shelf Notes is designed specifically for study management.

It provides:

* Category-based organization
* Fast search
* Easy access to study resources
* Public sharing of educational materials
* Dashboard with study statistics
* Simple and user-friendly interface

---

### 1.6 Project Goal

The goal of Shelf Notes is to help learners efficiently organize, manage, and access study materials from a single platform while reducing the time spent searching across multiple applications.

---

### 1.7 Expected User Experience

After using Shelf Notes, users should feel that all their study resources are organized, easy to locate, and accessible from one place through a clean and user-friendly interface.

## 2. Functional Requirements

### 2.1 Authentication

The system shall allow users to:

* Register using email and password.
* Login using email and password.
* Authenticate using JWT.
* Logout securely.
* Change their password.
* Edit their profile information.

> Forgot Password and social login (Google/GitHub) are out of scope for Version 1.

---

### 2.2 User Profile

Each user shall have:

* Name
* Email
* Profile Picture
* Bio

Users shall be able to update their profile information at any time.

---

### 2.3 Categories

Users shall be able to:

* Create categories.
* Rename categories.
* Delete categories.
* View all categories.
* Move study materials between categories.

If a category is deleted, the system shall ask whether to:

* Delete all study materials inside the category.
* Move all study materials to **Uncategorized**.

---

### 2.4 Study Materials

Users shall be able to:

* Upload PDF files.
* Upload Images.
* Upload DOCX files.
* Save YouTube links.
* View study materials.
* Edit study materials.
* Delete study materials.
* Download uploaded files.
* Organize study materials into categories.
* Add tags.
* Search study materials.
* Filter study materials.

---

### 2.5 Visibility

Each study material shall have one of the following visibility options:

* Private
* Public

Private materials are visible only to the owner.

Public materials can be discovered by all users.

---

### 2.6 Public Study Materials

Users shall be able to:

* Browse public study materials.
* View public study materials.
* Download public study materials.
* Bookmark public study materials.
* Like public study materials.

Users cannot edit or delete study materials owned by other users.

---

### 2.7 Dashboard

Each user shall have a dashboard displaying:

* Total Study Materials
* Total Categories
* Number of PDFs
* Number of Images
* Number of DOCX files
* Number of YouTube Links
* Public Study Materials
* Private Study Materials
* Study Streak
* Storage Used
* Recent Uploads

---

### 2.8 Search & Filtering

Users shall be able to search study materials using:

* Title
* Category
* Tags

Users shall also be able to:

* Sort by Newest
* Sort by Oldest
* Sort Alphabetically

---

### 2.9 Bookmarks

Users shall be able to:

* Bookmark public study materials.
* Remove bookmarks.
* View all bookmarked study materials.

---

### 2.10 Administration

The system shall support an ADMIN role for future expansion.

No dedicated admin dashboard will be included in Version 1.
