import React from 'react';

const Header = () => {
  // return은 화면을 그리겠다.
  return (
    <div className="container">
      <header className="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
        <div className="col-md-1 mb-2 mb-md-0">
          <a href="/home" className="d-inline-flex link-body-emphasis text-decoration-none">
            <img src={require('../logo.png')} alt="logo" width={40} />
          </a>
        </div>
      </header>
    </div>
  );
}

export default Header;