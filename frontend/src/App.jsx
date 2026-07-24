import AuditForm from "./components/AuditForm";
import AuditResult from "./components/AuditResult";
import { useState } from "react";
import "./App.css";

function App() {

    const [report, setReport] = useState(null);
    const [error, setError] = useState("");

    return (
        <div className="container">

            <h1>
                Website Auditor 🚀
            </h1>

            <p className="subtitle">
                Analyze website performance and SEO metrics
            </p>


            <AuditForm
                setReport={setReport}
                setError={setError}
            />


            {
                error && (
                    <div className="error">
                        ❌ {error}
                    </div>
                )
            }


            {
                report && (
                    <AuditResult report={report}/>
                )
            }

            <footer className="footer">
                Built for{" "}
                <a
                    href="https://digitalheroesco.com"
                    target="_blank"
                    rel="noopener noreferrer"
                >
                    Digital Heroes Training Task
                </a>
            </footer>

        </div>
    )
}

export default App;