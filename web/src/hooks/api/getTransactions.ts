import { useSharedAuth } from "../auth";
import { TransactionData } from "./postTransactions";
import { useApiGet } from "./requests/get";

export function useGetTransactions() {
    const { loading, apiGet } = useApiGet();
    const { authState } = useSharedAuth();

    async function getTransactions() {
        if(!authState) throw new Error("Not logged in");
        
        const response = await apiGet(`/transactions?redmondId=${authState.redmondId}`);
        if(!response.ok) throw new Error("Error getting transactions");

        const data = await response.json() as [TransactionData];
        return data.map(t => {
            t.timestamp = new Date(t.timestamp);
            return t;
        });
    }
    
    return { loading, getTransactions };
}
