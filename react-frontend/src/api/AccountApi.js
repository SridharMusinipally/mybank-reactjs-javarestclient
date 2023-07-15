import axios from "axios";

// we will use this amazing free api and declare our client
const client = (() => {
  return axios.create({
    baseURL: "https://localhost:8443"
  });
})();



// the request function which will destructure the response
// as it's shown in the API
const AccountApi = async function (options, store) {

  // success handler
  const onSuccess = function (response) {
    console.log('response:' + JSON.stringify(response));
    const { data } = response;
    console.log("response.data:" + response.data);
    return data;
  };

  // error handler
  const onError = function (error) {
    return Promise.reject(error.response);
  };

  // adding success and error handlers to client
  return client(options).then(onSuccess).catch(onError);
};

export default AccountApi;
