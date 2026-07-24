import { useState } from "react";
import { auditWebsite } from "../services/api";

function AuditForm({ setReport , setError}) {

    const [url, setUrl] = useState("");
    const [loading, setLoading] = useState(false);

    const handleSubmit = async () => {

        console.log("Sending URL:", url);

        try {

            setLoading(true);

            setError("");
            setReport(null);

            const response = await auditWebsite(url);

            setReport(response.data);

        }
        catch(error) {

            setReport(null);

            setError(
                error.response?.data?.error ||
                "Unable to audit website"
            );

        }
        finally {

            setLoading(false);

        }
    };


    return (

        <div className="audit-box">

            <input
                type="text"
                placeholder="Enter website URL"
                value={url}
                onChange={(e)=>setUrl(e.target.value)}
            />


            <button
                onClick={handleSubmit}
                disabled={loading}
            >

                {
                    loading
                        ? "Auditing..."
                        : "Audit Website"
                }

            </button>


            {
                loading &&
                <p className="loading">
                    🔍 Analyzing website...
                </p>
            }

        </div>

    );
}

export default AuditForm;