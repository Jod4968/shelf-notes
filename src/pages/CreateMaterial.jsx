function CreateMaterial({ onCancel }) {

    return (
        <div className="create-material-container">

            <div className="create-material-header">
                <div>
                    <h1>Create New Material</h1>
                    <p>Add your study resources to ShelfNotes.</p>
                </div>

                <button onClick={onCancel}>
                    ← Back
                </button>
            </div>


            <form className="create-material-form">

                {/* TITLE */}

                <div className="form-group">
                    <label>Title *</label>

                    <input
                        type="text"
                        placeholder="e.g. Java Collections"
                        maxLength={150}
                    />
                </div>


                {/* DESCRIPTION */}

                <div className="form-group">
                    <label>Description</label>

                    <textarea
                        placeholder="Describe your study material..."
                        maxLength={1000}
                        rows={4}
                    />
                </div>


                {/* MATERIAL TYPE */}

                <div className="form-row">

                    <div className="form-group">
                        <label>Material Type *</label>

                        <select defaultValue="">
                            <option value="" disabled>
                                Select material type
                            </option>

                            <option value="PDF">PDF</option>
                            <option value="IMAGE">IMAGE</option>
                            <option value="DOCX">DOCX</option>
                            <option value="YOUTUBE">YOUTUBE</option>
                        </select>
                    </div>


                    {/* VISIBILITY */}

                    <div className="form-group">
                        <label>Visibility *</label>

                        <select defaultValue="">
                            <option value="" disabled>
                                Select visibility
                            </option>

                            <option value="PRIVATE">Private</option>
                            <option value="UNLISTED">Unlisted</option>
                            <option value="PUBLIC">Public</option>
                        </select>
                    </div>

                </div>


                {/* CATEGORY */}

                <div className="form-group">
                    <label>Category *</label>

                    <select defaultValue="">
                        <option value="" disabled>
                            Select category
                        </option>
                    </select>
                </div>


                {/* FILE UPLOAD */}

                <div className="form-group">
                    <label>Upload File</label>

                    <input
                        type="file"
                    />

                    <small>
                        Upload your study material (optional).
                    </small>
                </div>


                {/* BUTTONS */}

                <div className="form-actions">

                    <button
                        type="button"
                        onClick={onCancel}
                    >
                        Cancel
                    </button>

                    <button type="submit">
                        Save Material
                    </button>

                </div>

            </form>

        </div>
    );
}

export default CreateMaterial;