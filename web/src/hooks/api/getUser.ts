import { useSharedAuth } from "../auth";
import { useApiGet } from "./requests/get";

export interface UserData {
    redmondId: string;
    cbu: string;
    cuil: string;
    balance: number;
}

export function useGetUser() {
    const { loading, apiGet } = useApiGet();
    const { authState } = useSharedAuth();

    async function getUser() {
        if(!authState) throw new Error("Not logged in");
        
        const response = await apiGet(`/users/${authState.redmondId}`);
        if(!response.ok) throw new Error("Error getting user");

        return await response.json() as UserData;
    }
    
    return { loading, getUser };
}
