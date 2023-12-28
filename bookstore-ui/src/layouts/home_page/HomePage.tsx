import {ExploreTopBooks} from "./components/ExploreTopBooks";
import {Carousel} from "./components/Carousel";
import {Heros} from "./components/Heroes";
import {LibraryServices} from "./components/LibraryServices";
import React from "react";

export const HomePage = () => {
    return (
        <>
            <ExploreTopBooks></ExploreTopBooks>
            <Carousel></Carousel>
            <Heros></Heros>
            <LibraryServices></LibraryServices>
        </>
    );
}