import React from 'react';
import { useSelector } from 'react-redux';
import { Link, useLocation } from 'react-router-dom';
import Divider from '@material-ui/core/Divider';
import Drawer from '@material-ui/core/Drawer';
import Hidden from '@material-ui/core/Hidden';
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemText from '@material-ui/core/ListItemText';
import HistoryIcon from '@material-ui/icons/History';
import AccountCircleIcon from '@material-ui/icons/AccountCircle';
import PeopleIcon from '@material-ui/icons/People';
import LocalAtmTwoToneIcon from '@material-ui/icons/LocalAtmTwoTone';
import ViewWeekTwoToneIcon from '@material-ui/icons/ViewWeekTwoTone';
import LocationOnRoundedIcon from '@material-ui/icons/LocationOnRounded';
import NotificationsTwoToneIcon from '@material-ui/icons/NotificationsTwoTone';
import LockTwoToneIcon from '@material-ui/icons/LockTwoTone';
import VpnKeyTwoToneIcon from '@material-ui/icons/VpnKeyTwoTone';
import {
	green, brown,
	blue, orange,
	red, grey,
	purple,
} from '@material-ui/core/colors';
import { useTheme } from '@material-ui/core/styles';

function LeftMenu(props) {
	const {
		handleDrawerToggle, classes, mobileOpen,
	} = props;
	const theme = useTheme();
	const location = useLocation();
	const loggedUser = useSelector((state) => state.loggedUser);

	const defaultMenuEntries = [
		{
			id: 1,
			name: 'Mis Taquillas',
			link: '/',
			icon: <LockTwoToneIcon style={{ color: orange[700] }} />,
		},
		{
			id: 2,
			name: 'Solicitar taquilla',
			link: '/request',
			icon: <ViewWeekTwoToneIcon style={{ color: brown[700] }} />,
		},
		{
			id: 3,
			name: 'Mis Datos',
			link: '/me',
			icon: <AccountCircleIcon style={{ color: blue[700] }} />,
		},
	];

	const adminMenuEntries = [
		{
			id: 4,
			name: 'Taquillas',
			link: '/admin/lockers',
			icon: <ViewWeekTwoToneIcon style={{ color: brown[700] }} />,
		},
		{
			id: 5,
			name: 'Solicitudes',
			link: '/admin/requests',
			icon: <NotificationsTwoToneIcon style={{ color: red[500] }} />,
		},
		{
			id: 6,
			name: 'Localizaciones',
			link: '/admin/locations',
			icon: <LocationOnRoundedIcon style={{ color: blue[900] }} />,
		},
		{
			id: 7,
			name: 'Préstamos',
			link: '/admin/rentals',
			icon: <VpnKeyTwoToneIcon style={{ color: purple[900] }} />,
		},
		{
			id: 8,
			name: 'Pagos',
			link: '/admin/payments',
			icon: <LocalAtmTwoToneIcon style={{ color: green[700] }} />,
		},
		{
			id: 9,
			name: 'Usuarios',
			link: '/admin/users',
			icon: <PeopleIcon style={{ color: orange[700] }} />,
		},

	];

	const links = (linkList) => linkList.map((entry) => (
		<Link to={entry.link} key={entry.id}>
			<ListItem button selected={location.pathname === entry.link} key={entry.id}>
				<ListItemIcon key={entry.id}>
					{entry.icon}
				</ListItemIcon>
				<ListItemText primary={entry.name} />
			</ListItem>
		</Link>
	));

	let menuEntries;
	if (loggedUser.isAdmin) {
		menuEntries = [links(defaultMenuEntries), <Divider key="divider" />, links(adminMenuEntries)];
	} else {
		menuEntries = [links(defaultMenuEntries), <Divider key="divider" />];
	}

	const drawer = (
		<div>
			<div className={classes.toolbar} />
			<Divider />
			<List>
				{menuEntries}
			</List>
		</div>
	);
	return (
		<nav className={classes.drawer} aria-label="mailbox folders">
			{/* The implementation can be swapped with js to avoid SEO duplication of links. */}
			<Hidden smUp implementation="css">
				<Drawer
					variant="temporary"
					anchor={theme.direction === 'rtl' ? 'right' : 'left'}
					open={mobileOpen}
					onClose={handleDrawerToggle}
					classes={{
						paper: classes.drawerPaper,
					}}
					ModalProps={{
						keepMounted: true, // Better open performance on mobile.
					}}
				>
					{drawer}
				</Drawer>
			</Hidden>
			<Hidden xsDown implementation="css">
				<Drawer
					classes={{
						paper: classes.drawerPaper,
					}}
					variant="permanent"
					open
				>
					{drawer}
				</Drawer>
			</Hidden>
		</nav>
	);
}

export default LeftMenu;
