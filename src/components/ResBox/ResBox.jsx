import React from 'react';
import "./style.scss"

const ResBox = (info) => {
    const { imageDataList } = info;
    return (
        <>
            <div className="resBox">
                {imageDataList?.map((imageData, index) => (
                    <img key={index} src={imageData} alt={`image${index}`} />
                ))}
            </div>
        </>
    )
}

export default ResBox;
