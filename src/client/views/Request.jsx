import React, { useEffect, useState } from 'react';
import { useSelector } from 'react-redux';
import { getLockersScaffold, scaffolds } from '../utils/tableScaffolds';
import {
	getAllLockers, createLocker,
	updateLocker, removeLocker,
} from '../utils/api/lockers';
import {
	getAllRentals, createRental, createSoli,
	updateRental, removeRental,
} from '../utils/api/rentals';
import { getAllLocations } from '../utils/api/locations';
import { makeStyles } from '@material-ui/core/styles';
import ListSubheader from '@material-ui/core/ListSubheader';
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemText from '@material-ui/core/ListItemText';
import Collapse from '@material-ui/core/Collapse';
import ExpandLess from '@material-ui/icons/ExpandLess';
import ExpandMore from '@material-ui/icons/ExpandMore';
import AlertDialog from '../components/AlertDialog';
import Button from '@material-ui/core/Button';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';
import getSession from '../utils/api/session';

const useStyles = makeStyles((theme) => ({
	root: {
		width: '100%',
		maxWidth: 360,
		backgroundColor: theme.palette.background.paper,
	},
	nested: {
		paddingLeft: theme.spacing(4),
	},
}));
const Request = (props) => {
	const classes = useStyles();
	const [open, setOpen] = React.useState(false);
	const [newRental, setNewRental] = React.useState({});
	const [confirm, setConfirm] = React.useState(false);
	const locations = useSelector((state) => state.locations);
	const lockers = useSelector((state) => state.lockers);
	const loggedUser = useSelector((state) => state.loggedUser);
	const [aux, setAux] = React.useState('');

	useEffect(() => {
		getSession();
	}, []);
	useEffect(() => {
		getAllLockers();
	}, []);

	useEffect(() => {
		getAllLocations();
	}, []);

	const handleClick = (location) => {
		setOpen(!open);
		setAux(location.name);
	};
	const handleClickOpen = (lockerId) => {
		console.log(lockerId);
		setConfirm(true);
		setNewRental({
			lockerId,
			userId: loggedUser.id,
			deposit: 10,
		});
	};
	return (
		<>
			<List
				component="nav"
				aria-labelledby="nested-list-subheader"
				subheader={(
					<ListSubheader component="div" id="nested-list-subheader">
						Seleccione una taquilla de la lista
					</ListSubheader>
				)}
				className={classes.root}
			>
				{locations.map((location) => {
					return(
						<div>
							<ListItem
								button
								onClick={() => handleClick(location)}
							>
								<ListItemText primary={location.name} />
								{open && aux === location.name ? (
										<ExpandLess />
									) : (
										<ExpandMore />
									)}
							</ListItem>
							<Collapse in={open} timeout="auto" unmountOnExit>
								<List component="div" disablePadding>
									{lockers.filter((locker)=> locker.locationId == location.id).map((subItem) => {
										return (
											<ListItem
												button
												className={classes.nested}
												onClick={() => handleClickOpen(subItem.id)}
											>
												<ListItemText primary={'Taquilla ' + (subItem.lockerNumber).toString()} />
											</ListItem>
										)
									})}
								</List>
							</Collapse>
						</div>
				)}
				)}
			</List>
			{confirm ?  <AlertDialog newRental={newRental} /> : <></> }
		</>
	)
};

export default Request;
