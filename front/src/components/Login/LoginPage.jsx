import React, {useState} from 'react'
import {useDispatch} from 'react-redux'
import {loginUser} from '../../_actions/userAction'

const LoginPage = () => {
    const dispatch = useDispatch()
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
        dispatch(loginUser(state)).then(res => {
            console.log(res)
        })
    }

    return (
        <div>
            <h1>Login</h1>
            <form onSubmit={handleSubmit}>
                <label htmlFor="">Id</label>
                <input type="text" name="userId" value={userId} onChange={handleInput} />

                <label htmlFor="">Password</label>
                <input type="password" name="password" value={password} onChange={handleInput} />

                <button type="submit">Register</button>
            </form>
        </div>
    )
}

export default LoginPage