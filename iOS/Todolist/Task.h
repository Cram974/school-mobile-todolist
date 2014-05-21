//
//  Task.h
//  Todolist
//
//  Created by Marc DIJOUX on 21/05/2014.
//  Copyright (c) 2014 Marc Dijoux. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <CoreData/CoreData.h>


@interface Task : NSManagedObject

@property (nonatomic, retain) NSString * name;
@property (nonatomic, retain) NSNumber * priority;
@property (nonatomic, retain) NSString * category;

@end
