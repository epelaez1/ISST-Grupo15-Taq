export function setPaymentMethod(paymentMethod) {
	return { type: 'SET_PAYMENT_METHOD', payload: { paymentMethod } };
}

export function setPaymentMethods(paymentMethods) {
	return { type: 'SET_PAYMENT_METHODS', payload: { paymentMethods } };
}

export function removePaymentMethods(paymentMethods) {
	return { type: 'REMOVE_PAYMENT_METHODS', payload: { paymentMethods } };
}

export function removePaymentMethod(paymentMethod) {
	return { type: 'REMOVE_PAYMENT_METHOD', payload: { paymentMethod } };
}

export function updatePaymentMethods(paymentMethods) {
	return { type: 'UPDATE_PAYMENT_METHODS', payload: { paymentMethods } };
}

export function updatePaymentMethod(paymentMethod) {
	return { type: 'UPDATE_PAYMENT_METHOD', payload: { paymentMethod } };
}
