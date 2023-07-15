import AccountApi from "../api/AccountApi";

export default class AccountService {

  static getMonthlyBalance(accountNumber, asOfDate) {
    return AccountApi({
      url: "/api/account/getmonthlybalance/" + accountNumber + "/" + asOfDate,
      method: "GET"
    });
  }
  static getCumulativeBalance(accountNumber, fromDate) {
    return AccountApi({
      url: "/api/account/getcumulativebalance/" + accountNumber + "/" + fromDate,
      method: "GET"
    });
  }
}
