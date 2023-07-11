import { useApiRequest } from "./request";

export function useApiGet() {
    const { loading, apiRequest } = useApiRequest();

    async function apiGet(path: string, body?: unknown) {
        return await apiRequest({
            method: "GET",
            path,
            body
        });
    } 
    
    return { loading, apiGet };
}