function AuditResult({report}) {

    return (

        <div className="result-card">

            <h2>
                Website Audit Report
            </h2>


            <div className="grid">


                <div>
                    <strong>URL</strong>
                    <p>{report.url}</p>
                </div>


                <div>
                    <strong>Status</strong>

                    <p className={
                        report.status === 200
                            ? "success"
                            : "failed"
                    }>
                        {report.status}
                    </p>

                </div>


                <div>
                    <strong>Response Time</strong>
                    <p>{report.responseTimeMs} ms</p>
                </div>


                <div>
                    <strong>Title</strong>
                    <p>{report.title}</p>
                </div>


                <div>
                    <strong>Meta Description</strong>
                    <p>
                        {report.metaDescription || "Missing"}
                    </p>
                </div>


                <div>
                    <strong>H1 Count</strong>
                    <p>{report.h1Count}</p>
                </div>


                <div>
                    <strong>Images Missing Alt</strong>
                    <p>{report.imagesWithoutAlt}</p>
                </div>


                <div>
                    <strong>Word Count</strong>
                    <p>{report.wordCount}</p>
                </div>


            </div>

        </div>

    )

}

export default AuditResult;