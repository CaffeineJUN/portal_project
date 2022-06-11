import React, {useState} from 'react'
import {useDispatch} from 'react-redux'
import {useNavigate} from 'react-router-dom'
import {updateUser} from '../../_actions/userAction'
import axios from 'axios'

import styles from './UpdatePage.module.css'

const UpdatePage = () => {
    const dispatch = useDispatch()
    const navigate = useNavigate()

    const [state, setState] = useState({
        password: '',
    })

    const {password} = state

    const handleInput = e => {
        let {name, value} = e.target
        setState({...state, [name]: value})
    }

    const handleSubmit = e => {
        e.preventDefault()
        dispatch(updateUser(state)).then(res => {
            console.log(res)
            navigate('/')
        })
    }

    const deleteUserBtn = () => {
        axios.delete('api/user/delete').then(res => {
            console.log(res)
            navigate('/')
        })
    }

    return (
        <div className={styles.container}>
            <h1>유저정보 수정</h1>
            <form onSubmit={handleSubmit}>
                <label htmlFor="">비밀번호 수정</label>
                <input type="password" name="password" value={password} onChange={handleInput} />

                <button type="submit">update</button>
            </form>

            <button onClick={deleteUserBtn}>Delete User</button>
        </div>
    )
}

export default UpdatePage
