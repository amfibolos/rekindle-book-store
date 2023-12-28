import React from 'react';
import './App.css';
import {Navbar} from './layouts/navbar_footers/Navbar'
import {Footer} from "./layouts/home_page/components/Footer";
import {HomePage} from "./layouts/home_page/HomePage";

function App() {
    return (
        <div>
            <Navbar></Navbar>
            <HomePage></HomePage>
            <Footer></Footer>
        </div>

    );
}

export default App;
