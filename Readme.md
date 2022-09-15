# AcmeBank

In this project, we created a banking application in Java that provides the bank teller with various functions, including:

- Register New Customer
- View Customer Profile
- Update Customer Details
- Delete Customer Account
- View Bank Account
- Create New Bank Account
- Delete Bank Account

The customer must have a customer account before they can create a bank account. This allows the customer to have a selection of different bank accounts:
- Personal
- Business
- ISA

Depending on the type of bank account created, they will be able to access various functions through "View Bank Account" option, including:

- Check Balance
- Withdraw
- Transfer (money to another account)
- International Transfer
- Deposit
- Create Loan
- Pay Loan
- View Transactions
- Set up standing order (a feature not fully implemented)
- Set up direct debit (a feature not fully implemented)

You can check out our class and menu structure here: [Miro Board](https://miro.com/app/board/uXjVPbBohZQ=/)

## Getting started

To explore the codebase follow this set-up:

1. Clone repo
2. Run the 'Menu' class
3. Use the following bank teller account details: User: "Admin", Password: "Password1234".

For convenience, we created a couple of customer and bank accounts in the main method (found in Main class) to allow users of this source code to experiment with the project.
Alternatively, you can create your own customer/bank accounts in the bank teller options list.

Account 1:
- Account Code: 4000
- Bank Account Code: 2000

Account 2:
- Account Code: 4001
- Bank Account Code: 2001

Account 3:
- Account Code: 4002
- Bank Account Code: 2002

Note: The bank teller admin account is not encrypted as this was not a goal for this project.