import React from 'react';
import './App.css';
import {Navbar} from './layouts/navbar_footers/Navbar'
import {Footer} from "./layouts/home_page/components/Footer";
import {HomePage} from "./layouts/home_page/HomePage";
import SignInSide from "./layouts/home_page/components/SignInSide";

function App() {
    return (
        <div>
            <Navbar></Navbar>
            <SignInSide></SignInSide>
            <Footer></Footer>
        </div>

    );
}

export default App;
