import React from 'react';

const InlineError = ({text}) => {
    return (
        <p style = {{ color: '#cc0000' }} >{text}</p>
    );
};

export default InlineError;