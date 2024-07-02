import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';

// 화면전환에 필요한 react-router-dom
import { BrowserRouter } from 'react-router-dom';

// 만든 페이지 추가
import Header from './layout/Header'

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <div className='App h-full w-full'>
    <BrowserRouter>
      <div id="wrapper" className='flex flex-col h-screen'>
        {/* head */}
        <Header />
        {/* main-content */}
        <App />
        {/* footer */}
      </div>
    </BrowserRouter>
  </div>
);