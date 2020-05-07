export function setPayment(payment) {
	return { type: 'SET_PAYMENT', payload: { payment } };
}

export function setPayments(payments) {
	return { type: 'SET_PAYMENTS', payload: { payments } };
}

export function removePayments(payments) {
	return { type: 'REMOVE_PAYMENTS', payload: { payments } };
}

export function removePayment(payment) {
	return { type: 'REMOVE_PAYMENT', payload: { payment } };
}

export function updatePayments(payments) {
	return { type: 'UPDATE_PAYMENTS', payload: { payments } };
}

export function updatePayment(payment) {
	return { type: 'UPDATE_PAYMENT', payload: { payment } };
}
