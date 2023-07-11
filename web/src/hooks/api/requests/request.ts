import { useState } from "react";
import { useSharedAuth } from "../../auth";
import { useNavigate } from "react-router-dom";
import { API_URL } from "../../../constants";

export interface ApiRequest {
    path: string;
    method: "GET" | "POST" | "PUT" | "PATCH" | "DELETE";
    body?: unknown;
}

export function useApiRequest () {
    const [loading, setLoading] = useState(false);
    const { authState, setAuthState, setAuthToken } = useSharedAuth();

    const navigate = useNavigate();

    async function apiRequest(request: ApiRequest) : Promise<Response> {
        setLoading(true);

        const authToken = authState?.authToken;
        const refreshToken = authState?.refreshToken;

        const headers = new Headers({
            "Content-Type": "application/json"
        });
        if(authToken) {
            headers.set("Authorization", `Bearer ${authToken}`);
        }

        const requestFunc = async () => fetch(`${API_URL}${request.path}`, {
            method: request.method,
            headers,
            body: JSON.stringify(request.body)
        });

        let response;

        try {
            response = await requestFunc();
        } catch(err) {
            setLoading(false);
            throw err;
        }

        if(refreshToken == null || response.status != 401) {
            setLoading(false);
            return response;
        }

        headers.set("Authorization", `Bearer ${refreshToken}`);

        try {
            response = await requestFunc();
        } catch(err) {
            setLoading(false);
            throw err;
        }

        if(response.status == 401) {
            setAuthState(null);
            setLoading(false);
            navigate("/");
            throw new Error("Unauthorized");
        }

        if(response.headers.has("Authorization")) {
            const newToken = response.headers.get("Authorization")!.split(" ")[1];
            setAuthToken(newToken);
        }

        setLoading(false);
        return response;
    }

    return { apiRequest, loading };
}
