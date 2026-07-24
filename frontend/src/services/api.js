import axios from "axios";

const API_URL = "https://website-auditor-production-36bc.up.railway.app/api/audit";

export const auditWebsite = (url) => {
    return axios.post(`${API_URL}`, {
        url: url
    });
};