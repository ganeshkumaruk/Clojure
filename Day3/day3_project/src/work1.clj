(ns work1)
;-- Use refs to create a vector of accounts in memory. Create debit
;-- and credit functions to change the balance of an account.
 
 
;-- Define the accounts vector. Each element in this vector will be
;-- a map with the keys:
;--
;-- :account The account number.
;-- :balance The amount in the account.
;--
;-- NOTE: Since this is being defined as a ref value, it can only be
;-- changed within the context of a transaction.
(def accounts (ref []))
 
 
;-- --------------------------------------------------------- --
;-- --------------------------------------------------------- --
 
 
;-- I credit the account with the given number by the given amount.
;-- I return the updated accounts collection.
(defn
credit
[accounts account-number credit-ammount]
 
;-- Since this is a vector, we don't have direct access to the
;-- account with the given number. As such, we'll have to map
;-- the current accounts collection onto a new collection,
;-- looking at each account as we go.
(map
(fn
[account]
 
;-- Check to see if the current account has the number
;-- we are looking for.
(if
(= account-number (:account account))
 
;-- This is our target account; as such, return a new
;-- map with an augmented balance.
(assoc
account
:balance
(+ (:balance account) credit-ammount)
)
 
;-- This is NOT the account; as such, simply return
;-- the unaltered account so that it can be mapped
;-- back onto the result collection.
account
)
)
accounts
)
)
 
 
;-- I debit the account with the given number by the given ammount.
;-- I return the updated accounts collection.
;--
;-- NOTE: I simply use the credit function, but with a negative
;-- credit ammount.
(defn
debit
[accounts account-number debit-ammount]
 
;-- Pass this operation off to the credit but negate ammount.
(credit
accounts
account-number
(- debit-ammount)
)
)
 
 
;-- --------------------------------------------------------- --
;-- --------------------------------------------------------- --
 
 
;-- First, we need to push some accounts onto the accounts queue.
;-- Since this is atlering the collection, we need perform this
;-- within a transaction.
(dosync
 
;-- Use the (conj) function as our mutation method. We can
;-- then conjoin the given account maps onto the collection.
(alter
accounts
conj
{
:account "123"
:balance 50
}
{
:account "456"
:balance 50
}
{
:account "789"
:balance 50
}
)
 
)
 
 
;-- Now that we populated our accounts collection, we can update
;-- some of the accounts in the collection. Again, since this is
;-- a ref value, we need to alter the collection from within a
;-- transaction.
(dosync
 
;-- Credit $50 to the account "456".
(alter
accounts
credit
"456"
50
)
 
;-- Debit $25 from the account "123".
(alter
accounts
debit
"123"
25
)
 
)
 
 
;-- --------------------------------------------------------- --
;-- --------------------------------------------------------- --
 
 
;-- Print out updated accounts collection.
(println @accounts)