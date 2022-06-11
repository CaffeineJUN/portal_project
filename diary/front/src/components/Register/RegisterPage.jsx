import React, {useState} from 'react'
import {useDispatch} from 'react-redux'
import {registUser} from '../../_actions/userAction'
import {useNavigate} from 'react-router-dom'

import styles from './RegisterPage.module.css'

const RegisterPage = () => {
    const dispatch = useDispatch()
    const navigate = useNavigate()

    const [state, setState] = useState({
        userId: '',
        password: '',
    })

    const {userId, password} = state

    const handleInput = e => {
        let {name, value} = e.target
        setState({...state, [name]: value})
    }

    const handleSubmit = e => {
        e.preventDefault()
        // console.log(state)
        dispatch(registUser(state)).then(res => {
            console.log(res)
            navigate('/login')
        })
    }

    return (
        <div className={styles.container}>
            <h1>회원가입</h1>
            <form onSubmit={handleSubmit}>
                <label htmlFor="">ID</label>
                <input type="text" name="userId" value={userId} onChange={handleInput} />

                <label htmlFor="">PASSWORD</label>
                <input type="password" name="password" value={password} onChange={handleInput} />

                <button type="submit">Register</button>
            </form>
        </div>
    )
}

export default RegisterPage
