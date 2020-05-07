import {
	fetchGet, fetchPost,
	fetchDelete, fetchPut,
} from '../asyncRequests';

import {
	setUser, setUsers,
	updateUser as updateUserAction,
	removeUser as removeUserAction,
} from '../../redux/actions/users';

import store from '../../redux/store';

const { dispatch } = store;

export const getAllUsers = () => fetchGet('/api/v1/admin/users')
	.then((r) => r.json())
	.then((users) => dispatch(setUsers(users)));

export const createUser = (newUser) => fetchPost('/api/v1/admin/user', newUser)
	.then((r) => r.json())
	.then((user) => dispatch(setUser(user)));

export const updateUser = (newUser, oldUser) => {
	const url = `/api/v1/admin/user/${oldUser.id}`;
	return fetchPut(url, newUser)
		.then((r) => r.json())
		.then((user) => dispatch(updateUserAction(user)));
};

export const removeUser = (user) => {
	const url = `/api/v1/admin/user/${user.id}`;
	return fetchDelete(url, {})
		.then((r) => r.json())
		.then(() => dispatch(removeUserAction(user)));
};
