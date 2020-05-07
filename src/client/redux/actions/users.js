export function makeAdmin() {
	return { type: 'MAKE_ADMIN', payload: {} };
}

export function setUser(user) {
	return { type: 'SET_USER', payload: { user } };
}

export function setUsers(users) {
	return { type: 'SET_USERS', payload: { users } };
}

export function removeUsers(users) {
	return { type: 'REMOVE_USERS', payload: { users } };
}

export function removeUser(user) {
	return { type: 'REMOVE_USER', payload: { user } };
}

export function updateUsers(users) {
	return { type: 'UPDATE_USERS', payload: { users } };
}

export function updateUser(user) {
	return { type: 'UPDATE_USER', payload: { user } };
}
