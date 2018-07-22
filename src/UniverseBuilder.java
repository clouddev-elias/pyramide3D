package Pyramide.pyramide;

import java.awt.*;
import java.awt.event.*;
import javax.media.j3d.*;
import javax.vecmath.*;

public class UniverseBuilder extends Object {

	// User-specified canvas

	Canvas3D canvas;

	// Scene graph elements to which the user may want access

	VirtualUniverse               universe;
	Locale                        locale;
	TransformGroup                vpTrans,vpRot;
	View                          view;

	public UniverseBuilder(Canvas3D c) {
		this.canvas = c;


		// Establish a virtual universe that has a single
		// hi-res Locale

		universe = new VirtualUniverse();
		locale = new Locale(universe);


		// Create a PhysicalBody and PhysicalEnvironment object

		PhysicalBody body = new PhysicalBody();
		PhysicalEnvironment environment = new PhysicalEnvironment();


		// Create a View and attach the Canvas3D and the physical
		// body and environment to the view.

		view = new View();
		view.addCanvas3D(c);
		view.setPhysicalBody(body);
		view.setPhysicalEnvironment(environment);


		// Create a BranchGroup node for the view platform

		BranchGroup vpRoot = new BranchGroup();


		// Create a ViewPlatform object, and its associated
		// TransformGroup object, and attach it to the root of the
		// subgraph. Attach the view to the view platform.

		Transform3D t = new Transform3D();
		t.set(new Vector3f(0.0f, 0.0f, 10.0f));
		
		Transform3D s = new Transform3D();
		s.rotX(-Math.PI/4);
		s.mul(t);	
		ViewPlatform vp = new ViewPlatform();
		//vpTrans = new TransformGroup(t);
		vpTrans = new TransformGroup(s);
		
		Transform3D r = new Transform3D();
		vpRot = new TransformGroup(r);
		vpRot.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		
		
		// Connecting the tree together
		vpTrans.addChild(vp);
		vpRot.addChild(vpTrans);
		//vpRoot.addChild(vpTrans);
		vpRoot.addChild(vpRot);

		view.attachViewPlatform(vp);

		// Create a new Behavior object that will perform the
		// desired operation on the specified transform and add
		// it into the scene graph.

		Transform3D xAxis = new Transform3D();
		xAxis.rotZ(-Math.PI/2);
		Transform3D yAxis = new Transform3D();
		Alpha rotationAlpha = new Alpha(
				-1, Alpha.INCREASING_ENABLE,
				0, 0, 2000, 0, 0, 0, 0, 0);
		RotationInterpolator rotator = new RotationInterpolator(
				rotationAlpha, vpRot, xAxis,
				0.0f, (float) Math.PI*2.0f);
	
		BoundingSphere bounds = new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);
		rotator.setSchedulingBounds(bounds);
		vpRot.addChild(rotator);

		// Attach the branch graph to the universe, via the
		// Locale. The scene graph is now live!

		locale.addBranchGraph(vpRoot);
	}

	public void addBranchGraph(BranchGroup bg) {
		locale.addBranchGraph(bg);
	}

}





