import { useEffect, useState } from "react";
import { useQuery } from "react-query";
import AccountService from "./service/AccountService";

export default function App() {

    let accountNumber;
    let mbAsOfDate;
    let cbFromDate;

      const { data: monthlyData, refetch: refetchMonthlyBal} = useQuery(["monthlybalance"], () => AccountService.getMonthlyBalance(accountNumber.value, mbAsOfDate.value), {enabled: false});
      const { data: cumulativeData, refetch: refetchCumulativeBal } = useQuery(["cumulativebalance"],    () => AccountService.getCumulativeBalance(accountNumber.value, cbFromDate.value), {enabled: false});

      useEffect(() => {
          const handleClick = () => {
            accountNumber = document.querySelector('#accountNumber');
            mbAsOfDate = document.querySelector('#mbAsOfDate');
            cbFromDate = document.querySelector('#cbFromDate');

            console.log("accountNumber.value:" + accountNumber.value);
            console.log("mbAsOfDate.value:" + mbAsOfDate.value);
            console.log("cbFromDate.value:" + cbFromDate.value);

            refetchMonthlyBal();
            refetchCumulativeBal();
          };

          // Get the button element
          const button = document.querySelector('#ShowBalance');

          // Add event listener
          button.addEventListener('click', handleClick);

          // Clean-up function to remove the event listener
          return () => {
            button.removeEventListener('click', handleClick);
          };
        }, []);


//  const monthlyBalanceValue = monthlyData.monthlyBalance;
//  const cumulativeBalanceValue = cumulativeData.cumulativeBalance;

  console.log('monthlyData:' + JSON.stringify(monthlyData));
  console.log('cumulativeData:' + JSON.stringify(cumulativeData));

//    if(cumulativeBalanceValue){
//        console.log("cumulativeBalanceValue:" + cumulativeBalanceValue);
//    }
//
//    if(isLoadingMonthlyBalance){
//        console.log("monthlyBalanceValue:" + monthlyBalanceValue);
//    }

    console.log("From App.js");



//         constructor(props) {
//           super(props);
//           this.handleNameChange = this.handleNameChange.bind(this);
//           this.handleEmailChange = this.handleEmailChange.bind(this);
//           this.handlePhoneChange = this.handlePhoneChange.bind(this);
//         }
//         handleNameChange(e) {
//           this.props.onChange('name', e.target.value);
//         }
//         handleEmailChange(e) {
//           this.props.onChange('email', e.target.value);
//         }
//         handlePhoneChange(e) {
//           this.props.onChange('phone', e.target.value);
//         }

    const user = {name: 'abc', email: 's@s.com', phone:'9999999999'}
  return (
       <div className="App">
              <p/>

              <label>
                  Account Number:
                  <input type="text" name="accountNumber" id="accountNumber" />
              </label>

              <br/>
              <br/>

              <label>
                  Monthly Balance As Of Date (dd-MM-yyyy):
                  <input type="text" name="mbAsOfDate" id="mbAsOfDate" />
              </label>

              <br/>
              <br/>


              <label>
                  Cumulative Balance from Date (dd-MM-yyyy):
                  <input type="text" name="cbFromDate" id="cbFromDate" />
              </label>

              <br/>
              <br/>
              <br/>
              <br/>

              <input type="button" value="Show Balance" name="ShowBalance" id="ShowBalance"/>

              <p/>
              <p/>
              <p/>

              <br/>
              <br/>

              Results:

              <br/>
              <br/>

              <div/>
              <div/>
              <div/>
              <div/>
              <div/>
              <div style={{ marginLeft: "50px", border: "5px solid black" }}>
                {monthlyData && monthlyData.accountNumber && <p>Account Number: {monthlyData.accountNumber} </p>}
                {monthlyData && monthlyData.monthlyBalance && <p>Monthly Balance Value: {monthlyData.monthlyBalance} </p>}
                {monthlyData && monthlyData.asOfDate && <p>Monthly Balance As Of Date: {monthlyData.asOfDate} </p>}
                {cumulativeData && cumulativeData.cumulativeBalance && <p>Cumulative Balance Value: {cumulativeData.cumulativeBalance} </p>}
                {cumulativeData && cumulativeData.asOfDate && <p>Cumulative Balance As Of Date: {cumulativeData.asOfDate} </p>}
              </div>
          </div>
     );
}

