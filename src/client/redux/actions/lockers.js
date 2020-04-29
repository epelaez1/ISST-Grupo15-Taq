export function setLocker(locker) {
	console.log(locker);
	return { type: 'SET_LOCKER', payload: { locker } };
}
export function setLockers(lockers) {
	return { type: 'SET_LOCKERS', payload: { lockers } };
}

export function removeLockers(lockers) {
	return { type: 'REMOVE_LOCKERS', payload: { lockers } };
}

export function removeLocker(locker) {
	return { type: 'REMOVE_LOCKER', payload: { locker } };
}

export function updateLockers(lockers) {
	return { type: 'UPDATE_LOCKERS', payload: { lockers } };
}

export function updateLocker(locker) {
	return { type: 'UPDATE_LOCKER', payload: { locker } };
}
