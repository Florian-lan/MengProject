import React from 'react';
import { Carousel } from 'antd';
import "./style.scss";

const Card = () => {
    return (
        <>
            <Carousel effect="fade" slide='false' fade='false'>
                <div>
                    <h3 className="carousel-content">
                        <img src="https://github.com/Florian-lan/MengProject/blob/main/public/images/quan2.JPG" alt="" />
                    </h3>


                </div>
                <div>
                    <h3 className="carousel-content">1</h3>

                </div>
                <div>
                    <h3 className="carousel-content">1</h3>
                </div>
                <div>
                    <h3 className="carousel-content">1</h3>
                </div>
            </Carousel>
        </>
    )

}
export default Card;