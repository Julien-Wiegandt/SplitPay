# SplitPay
Full java project


## How to run and try the SplitPay App
### Run the SplitPay project
Open your command line terminal and enter the following command :
> git clone https://github.com/AyoubMoujane/SplitPay.git

Then open the SplitPay project with IntelliJ IDE

Go on File>Project Structure...>Project 

And change the Project SDK to corretto-1.8 version 1.8.0_275

Make sure that the maven project loads correctly (load dependencies)

Go in the maven section on the top right of the screen >split-pay>Lifecycle>compile

The project is now compiled and usable

### Run the Split Server
Go to src>main>java>server>main.java
Run the main method

The split server is now started, it doesn't have user interface so don't worry nothing will pop up

### Run a SplitPay App
Go to src>main>java>main>SplitPay.java
Run the main method

A SplitPay app is now launched, it has a user interface this time :)

### Things to know
##### Some NormalUser login credentials
test@test.com, psd->splitpay 

bertha@hotmail.com, 0611223344, psd->splitpay

##### Some StoreOwner login credentials
delarte34@hotmail.com, 0767342312, psd->delarte34

bk@gmail.com, psd->splitpay

### Things to test
# Freesplit saloon
You can create a split which is a saloon that can be joined with a code by normal users
Try creating a saloon with a normal user and join with other users
# Itemsplit saloon
This saloon can be created by a store owner, it's generated from a bill
The code is created and normal users can join, they can pick items in their cart and pay

##### The database
We are using an AWS online MySQL database, the database will be closed at the end of january 2021.
