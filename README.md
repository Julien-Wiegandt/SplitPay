# SplitPay
Full java project


##How to run and try the SplitPay App
### Get the SplitPay project
Open your command line terminal and enter the following command :
> git clone https://github.com/AyoubMoujane/SplitPay.git

Then open the SplitPay project with IntelliJ IDE

Go on File>Project Structure...>Project 

And change the Project SDK to corretto-1.8 version 1.8.0_275

Make shure that the maven project load correctly (load dependencies)

Go in the maven section on the top right of the screen >split-pay>Lifecycle>compile

The project is compiled you can now work on it

### Run the Split Server
Go to src>main>java>server>main.java
Run the main method

The split server is now started, it doesn't have user interface so don't worry

### Run a SplitPay App
Go to src>main>java>main>SplitPay.java
Run the main method

A SplitPay app is now lunched, it has a user interface this time :)

### Things to know
##### Some NormalUser login credentials
test@test.com, psd->splitpay 

bertha@hotmail.com, 0611223344, psd->splitpay

##### Some StoreOwner login credentials
delarte34@hotmail.com, 0767342312, psd->delarte34

bk@gmail.com, psd->splitpay

##### The database
We are using a AWS online MySQL database, so we will stop it at the end of january 2021.