import { useState, useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate} from "react-router-dom";

import 'react-toastify/dist/ReactToastify.css';

import { setHeaders, url } from "../slices/api";
import axios from "axios";




//import { useGetAllProductsQuery } from "../features/productApi";


const UserInfoTemplate = () => {
    const [user, setUser] = useState("");
    const [loading, setLoading] = useState(false);
    const auth = useSelector((state) => state.auth);

    // const { data, error, isLoading } = useGetAllProductsQuery();
    const dispatch = useDispatch();
    const navigate = useNavigate();

    useEffect(() => {

        async function fetchData() {
            setLoading(true);
  
              try {
                const res = await axios.get(
                    `${url}/user`, 
                    {                 
                      headers: setHeaders(auth.token),
                      params: {
                        email: auth.email,
                      },
                    },
                );

 
  
                    setUser(res.data);
  
            } catch (error) {

            }
            setLoading(false);

          };       
        fetchData();
    }, []);

    return (
    <div className="user-container">
        {loading ? (<p>Loading...</p>) :
            <div>
                <h2>User Info</h2>
                <div className="users">
                    
                    <div className="user">
                        <div className="details">
                            <p><span>User email:</span> {user.email} </p>
                        </div>
                        <p><span>User name:</span> {user.name} </p>
                    </div>
                </div>
            </div>
        }
    </div>
    );
}


export default UserInfoTemplate;