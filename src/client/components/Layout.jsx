import React from 'react';
import CssBaseline from '@material-ui/core/CssBaseline';
import { makeStyles } from '@material-ui/core/styles';
import NavBar from './NavBar';
import LeftMenu from './LeftMenu';

const drawerWidth = 240;

const useStyles = makeStyles((theme) => ({
	root: {
		display: 'flex',
	},
	title: {
		flexGrow: 1,
	},
	drawer: {
		[theme.breakpoints.up('sm')]: {
			width: drawerWidth,
			flexShrink: 0,
		},
	},
	appBar: {
		[theme.breakpoints.up('sm')]: {
			zIndex: theme.zIndex.drawer + 1,
		},
	},
	menuButton: {
		marginRight: theme.spacing(2),
		[theme.breakpoints.up('sm')]: {
			display: 'none',
		},
	},
	// necessary for content to be below app bar
	toolbar: theme.mixins.toolbar,
	drawerPaper: {
		width: drawerWidth,
	},
	content: {
		flexGrow: 1,
		padding: theme.spacing(3),
	},
}));

function ResponsiveDrawer(props) {
	const { footer, children } = props;
	const classes = useStyles();
	const [mobileOpen, setMobileOpen] = React.useState(false);

	const handleDrawerToggle = () => {
		setMobileOpen(!mobileOpen);
	};


	return (
		<div className={classes.root} style={{ height: '100%' }}>
			<CssBaseline />
			<NavBar classes={classes} handleDrawerToggle={handleDrawerToggle} />
			<LeftMenu
				handleDrawerToggle={handleDrawerToggle}
				mobileOpen={mobileOpen}
				classes={classes}
			/>
			<main className={classes.content} id="main" style={{ overflow: 'auto' }}>
				<div className={classes.toolbar} />
				<div className="main_section">{children}</div>
				{footer}
			</main>
		</div>
	);
}

export default ResponsiveDrawer;
