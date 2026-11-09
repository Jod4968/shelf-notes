
import { useState } from "react";
import "./CreateMaterial.css";

function CreateMaterial({ onCancel }) {

    const [formData, setFormData] = useState({
        title: "",
        description: "",
        materialType: "PDF",
        visibility: "PRIVATE",
        categoryId: "",
        file: null
    });

    return (
        <div className="create-material-page">
            <div className="create-material-container">

                <h1>Create Material</h1>

                <form className="create-material-form">

                    {/* Title */}
                    <div className="form-group">
                        <label>Title</label>
                        <input
                            type="text"
                            value={formData.title}
                            onChange={(e) =>
                                setFormData({
                                    ...formData,
                                    title: e.target.value
                                })
                            }
                            placeholder="Enter title"
                        />
                    </div>

                    {/* Description */}
                    <div className="form-group">
                        <label>Description</label>
                        <textarea
                            value={formData.description}
                            onChange={(e) =>
                                setFormData({
                                    ...formData,
                                    description: e.target.value
                                })
                            }
                            placeholder="Enter description"
                        />
                    </div>

                    {/* Material Type */}
                    <div className="form-group">
                        <label>Material Type</label>
                        <select
                            value={formData.materialType}
                            onChange={(e) =>
                                setFormData({
                                    ...formData,
                                    materialType: e.target.value
                                })
                            }
                        >
                            <option value="PDF">PDF</option>
                            <option value="IMAGE">Image</option>
                            <option value="DOCX">DOCX</option>
                            <option value="YOUTUBE">YouTube Video Link</option>
                        </select>
                    </div>

                    {/* Visibility */}
                    <div className="form-group">
                        <label>Visibility</label>
                        <select
                            value={formData.visibility}
                            onChange={(e) =>
                                setFormData({
                                    ...formData,
                                    visibility: e.target.value
                                })
                            }
                        >
                            <option value="PRIVATE">Private</option>
                            <option value="UNLISTED">Unlisted</option>
                            <option value="PUBLIC">Public</option>
                        </select>
                    </div>

                    {/* Category */}
                    <div className="form-group">
                        <label>Category ID</label>
                        <input
                            type="number"
                            value={formData.categoryId}
                            onChange={(e) =>
                                setFormData({
                                    ...formData,
                                    categoryId: e.target.value
                                })
                            }
                            placeholder="Enter category ID"
                        />
                    </div>

                    {/* File */}
                    <div className="form-group">
                        <label>Upload File</label>
                        <input
                            type="file"
                            onChange={(e) =>
                                setFormData({
                                    ...formData,
                                    file: e.target.files[0]
                                })
                            }
                        />
                    </div>

                    <div className="form-actions">
                        <button
                            type="button"
                            className="btn-cancel"
                            onClick={onCancel}
                        >
                            Cancel
                        </button>

                        <button
                            type="button"
                            className="btn-save"
                            onClick={() => console.log(formData)}
                        >
                            Save Material
                        </button>
                    </div>

                </form>
            </div>
        </div>
    );
}

export default CreateMaterial;
