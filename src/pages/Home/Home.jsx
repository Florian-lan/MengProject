import React, { useState } from "react";
import './style.scss';
import Card from "../../components/Card/Card";
import UploadImg from "../../components/UploadImg/UploadImg";

const Home = () => {

    return (
        <div className="home">
            <div className="content">
                <div className="carousel">
                    <Card />
                </div>
                <div className="upload">
                    <UploadImg />
                </div>
               

            </div>
        </div>


    )
}
export default Home;
