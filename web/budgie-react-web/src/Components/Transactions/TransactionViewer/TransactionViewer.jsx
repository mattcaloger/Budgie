import React from 'react'
import { TransactionsManager } from '../../../Contexts/TransactionsManager/TransactionsManager'
import AddBudgetItem from '../AddBudgetItem/AddBudgetItem'
import TransactionList from '../TransactionList/TransactionList'

export default function TransactionViewer() {
    return (
        <div>
            <TransactionsManager>
                <div>
                    <TransactionList/>
                </div>
            </TransactionsManager>           
        </div>
    )
}
