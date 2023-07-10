import { TransactionData } from "./postTransactions";
import { useApiGet } from "./requests/get";

export function useGetTransactionById() {
    const { loading, apiGet } = useApiGet();

    async function getTransactionById(id: string) {        
        const response = await apiGet(`/transactions/${id}`);
        if(!response.ok) throw new Error("Error getting transaction");

        const data = await response.json() as TransactionData;
        data.timestamp = new Date(data.timestamp);

        return data;
    }
    
    return { loading, getTransactionById };
}
