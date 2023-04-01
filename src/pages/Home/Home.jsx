import React, { useState } from "react";
import './style.scss';
import UploadImg from "../../components/UploadImg/UploadImg";
const Home = () => {

    return (
        <div className="home">
        <div className="content">
            <UploadImg />
        </div>
        </div>
        

    )
}
export default Home;
