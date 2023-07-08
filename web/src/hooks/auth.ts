import { useState } from "react";
import { useBetween } from "use-between";
import jwtDecode from 'jwt-decode';
import { API_URL } from "../constants";

export interface AuthState {
    authToken: string;
    refreshToken: string;
    redmondId: string;
}

function useAuth () {
    const [authState, setAuthState] = useState<AuthState | null>(() => {
        const token = localStorage.getItem("authToken");
        const refresh = localStorage.getItem("refreshToken");
        if(token && refresh) {
            const { sub: redmondId } = jwtDecode<{ sub: string }>(token);
            return {
                authToken: token,
                refreshToken: refresh,
                redmondId
            };
        }
        return null;
    });

    async function login(redmondId: string, password: string) : Promise<boolean> {
        const response = await fetch(`${API_URL}/login`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                redmondId,
                password
            })
        });

        if(!response.ok) return false;

        const { token, refresh } = await response.json() as { token: string, refresh: string };

        localStorage.setItem("refreshToken", refresh);
        localStorage.setItem("authToken", token);
        setAuthState({
            authToken: token,
            refreshToken: refresh,
            redmondId
        });

        return true;
    }

    function setAuthToken(token: string) {
        localStorage.setItem("authToken", token);
        setAuthState({
            ...authState!,
            authToken: token
        });
    }

    function logout() {
        localStorage.removeItem("authToken");
        localStorage.removeItem("refreshToken");
        setAuthState(null);
    }

    return {
        login,
        logout,
        authState,
        setAuthToken,
        setAuthState
    };
}

export const useSharedAuth = () => useBetween(useAuth);
