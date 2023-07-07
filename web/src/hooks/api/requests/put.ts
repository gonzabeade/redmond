import { useApiRequest } from "./request";

export function useApiPut() {
    const { loading, apiRequest } = useApiRequest();

    async function apiPut(path: string, body?: unknown) {
        return await apiRequest({
            method: "PUT",
            path,
            body
        });
    }
    
    return { loading, apiPut };
}
