export const url = "https://099ihs2ib0.execute-api.us-west-2.amazonaws.com/prod/api";
export const setHeaders = (token) => {
    const headers = {
        token: token,
      };
  
    return headers;
  };