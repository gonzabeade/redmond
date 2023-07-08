import { TransactionData } from "./postTransactions";
import { useApiGet } from "./requests/get";

export function useGetTransactionById() {
    const { loading, apiGet } = useApiGet();

    async function getTransactionById(id: string) {        
        const response = await apiGet(`/transactions/${id}`);
        if(!response.ok) throw new Error("Error getting transaction");

        return await response.json() as TransactionData;
    }
    
    return { loading, getTransactionById };
}
