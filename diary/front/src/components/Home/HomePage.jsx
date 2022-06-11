import React from 'react'
import axios from 'axios'
import {useNavigate} from 'react-router-dom'
import styles from './HomPage.module.css'

const HomePage = () => {
    const navigate = useNavigate()

    const hadleLogout = () => {
        axios.post('/api/logout').then(res => {
            console.log(res)
            navigate('/')
        })
    }

    return (
        <div className={styles.container}>
            <div className={styles.nav}>
                <div>한줄일기</div>
                <div>
                    <a href="/diary">다이어리</a>
                </div>
                <div>
                    <a href="/login">로그인</a>
                </div>
                <div>
                    <a href="/register">회원가입</a>
                </div>
                <button onClick={hadleLogout}>Logout</button>
            </div>
            <div className={styles.back}></div>
        </div>
    )
}

export default HomePage
