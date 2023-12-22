import axios from "axios";

const api = axios.create({
  baseURL: "http://localhost:8080/api/v1",
  headers: {
    "Content-Type": "application/json",
  },
});
api.interceptors.response.use(
  function (response) {
    return response;
  },
  function (error) {
    // TODO redirect to loging page
    console.error("Invalid Login.");
    return Promise.reject(error);
  }
);

export default api;
