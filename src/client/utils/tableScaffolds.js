import { LockerStates, RentalStates } from '../../server/constants';

// Common
const id = {
	field: 'id',
	title: 'ID',
	editable: 'never',
	width: 100,
};
const name = {
	field: 'name',
	title: 'Nombre',
	width: 200,
};

const description = {
	field: 'description',
	title: 'Descripción',
	cellStyle: {
		textOverflow: 'ellipsis',
		whiteSpace: 'nowrap',
		overflow: 'hidden',
		maxWidth: 100,
	},
};
const createdAt = {
	field: 'createdAt',
	title: 'Fecha de Creación',
	type: 'datetime',
	editable: 'never',
	filtering: false,
	cellStyle: {
		textOverflow: 'ellipsis',
		whiteSpace: 'nowrap',
		overflow: 'hidden',
		maxWidth: 100,
	},
};
const updatedAt = {
	field: 'updatedAt',
	title: 'Última modificación',
	type: 'datetime',
	editable: 'never',
	filtering: false,
	cellStyle: {
		textOverflow: 'ellipsis',
		whiteSpace: 'nowrap',
		overflow: 'hidden',
		maxWidth: 100,
	},
};

// Const
const lockerState = {
	field: 'lockerStateId',
	title: 'Estado',
	width: 200,
	type: 'numeric',
	lookup: {
		[LockerStates.UNAVAILABLE]: 'No Disponible',
		[LockerStates.AVAILABLE]: 'Disponible',
		[LockerStates.RESERVED]: 'Reservada',
		[LockerStates.RENTED]: 'Alquilada',
	},
};
const rentalState = {
	field: 'rentalStateId',
	title: 'Estado',
	width: 200,
	editable: 'onUpdate',
	type: 'numeric',
	lookup: {
		[RentalStates.REQUESTED]: 'Solicitud enviada',
		[RentalStates.RESERVED]: 'Taquilla reservada',
		[RentalStates.RENTED]: 'Alquiler en curso',
		[RentalStates.CLAIMED]: 'Alquiler expirado, llaves pendientes de devolución',
		[RentalStates.RENEW_REQUESTED]: 'Renovación solicitada',
		[RentalStates.RETURNED]: 'Alquiler finalizado',

	},
};

// Lockers
const lockerNumber = {
	field: 'lockerNumber',
	title: 'Nº',
	type: 'numeric',
	width: 100,
};
const location = {
	field: 'locationId',
	title: 'Localización',
	width: 200,
	lookup: {},
};

// Lockers
const phone = {
	field: 'phone',
	title: 'Teléfono',
};
const dni = {
	field: 'dni',
	title: 'DNI',
};
const email = {
	field: 'email',
	title: 'E-Mail',
};
const isAdmin = {
	field: 'isAdmin',
	title: 'Administrador',
	type: 'boolean',
	editable: 'onUpdate',
};

// Rentals:
const expirationDate = {
	field: 'expirationDate',
	title: 'Caducidad',
	type: 'datetime',
	filtering: false,
	cellStyle: {
		textOverflow: 'ellipsis',
		whiteSpace: 'nowrap',
		overflow: 'hidden',
		maxWidth: 100,
	},
};
const deposit = {
	field: 'deposit',
	title: 'Fianza',
	type: 'currency',
};

const lockerLocation = {
	field: 'Locker.Location.name',
	title: 'Localización',
	editable: 'never',
};

const userDNI = {
	field: 'User.dni',
	title: 'DNI',
	editable: 'never',
};
const lockerId = {
	field: 'lockerId',
	title: 'Taquilla',
	lookup: {},
};
const userId = {
	field: 'userId',
	title: 'E-Mail',
	lookup: {},
};

// Payments
const rentalId = {
	field: 'rentalId',
	title: 'Rental Id',
};

const quantity = {
	field: 'quantity',
	title: 'Cantidad',
	type: 'currency',
};

const paymentMethod = {
	field: 'PaymentMethod.name',
	title: 'Método de pago',
};

const userEmail = {
	field: 'User.email',
	title: 'E-Mail',
};
const allColumns = {
	locations: [id, name, description, createdAt, updatedAt],
	paymentMethods: [id, name, description, createdAt, updatedAt],
	lockers: [lockerNumber, location, lockerState, createdAt, updatedAt],
	users: [name, phone, dni, email, isAdmin, createdAt, updatedAt],
	rentals: [userId, lockerId, deposit, rentalState, expirationDate, userDNI, lockerLocation],
	payments: [userDNI, userEmail, rentalId, quantity, paymentMethod],
};

export const getLockersScaffold = (locations) => {
	const lookup = {};
	for (let i = 0; i < locations.length; i++) {
		lookup[locations[i].id] = locations[i].name;
	}
	const columns = [lockerNumber, { ...location, lookup }, lockerState, createdAt, updatedAt];
	return { id: 'id', title: 'Taquillas', columns };
};

export const getRentalsScaffold = (lockers, users) => {
	const lockersLookup = {};
	for (let i = 0; i < lockers.length; i++) {
		lockersLookup[lockers[i].id] = lockers[i].lockerNumber;
	}

	const usersLookup = {};
	for (let i = 0; i < users.length; i++) {
		usersLookup[users[i].id] = users[i].email;
	}
	const columns = [
		{ ...userId, lookup: usersLookup },
		{ ...lockerId, lookup: lockersLookup },
		deposit,
		rentalState,
		expirationDate,
		userDNI,
		lockerLocation,
	];
	return { id: 'id', title: 'Alquileres', columns };
};

export const scaffolds = {
	locations: {
		id: 'id',
		title: 'Localizaciones',
		columns: allColumns.locations,
	},
	paymentMethods: {
		id: 'id',
		title: 'Métodos de pago',
		columns: allColumns.paymentMethods,
	},
	lockers: {
		id: 'id',
		title: 'Taquillas',
		columns: allColumns.lockers,
	},
	users: {
		id: 'id',
		title: 'Usuarios',
		columns: allColumns.users,
	},
	rentals: {
		id: 'id',
		title: 'Alquileres',
		columns: allColumns.rentals,
	},
	payments: {
		id: 'id',
		title: 'Histórico de pagos',
		columns: allColumns.payments,
	},
};
