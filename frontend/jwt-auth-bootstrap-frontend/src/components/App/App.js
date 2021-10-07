import React, {useState} from "react";
import {Link, Route, BrowserRouter as Router, Switch} from "react-router-dom";

import AuthService from "../../services/authentication/auth.service"

import Login from "../AuthenticationPages/Login/Login";
import Register from "../AuthenticationPages/Register/Register";


const App = () => {
    const [currentUser, setCurrentUser] = useState(undefined);

    const logOut = () => {
        AuthService.logout();
    };

    return (
        <div>
            <Router>
                <nav className="navbar navbar-expand navbar-dark bg-dark">

                    <div className="navbar-nav mr-auto">
                        <h1>Welcome not logged in user</h1>
                    </div>

                    {currentUser ? (
                        <div className="navbar-nav ml-auto">
                            <li className="nav-item">
                                <h1>Welcome logged in user</h1>
                            </li>
                            <li className="nav-item">
                                <a href="/login" className="nav-link" onClick={logOut}>
                                    Log out
                                </a>
                            </li>
                        </div>
                    ) : (
                        <div className="navbar-nav ml-auto">
                            <li className="nav-item">
                                <Link to={"/login"} className="nav-link">
                                    Login
                                </Link>
                            </li>

                            <li className="nav-item">
                                <Link to={"/register"} className="nav-link">
                                    Register
                                </Link>
                            </li>
                        </div>
                    )}
                </nav>

                <div className="container mt-3">
                    <Switch>
                        <Route exact path="/login" component={Login}/>
                        <Route exact path="/register" component={Register}/>
                    </Switch>
                </div>
            </Router>

        </div>
    );

};

export default App;