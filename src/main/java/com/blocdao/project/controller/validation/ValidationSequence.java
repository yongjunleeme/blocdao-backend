package com.blocdao.project.controller.validation;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

@GroupSequence({Default.class, ValidationGroups.NotEnptyGroup.class, ValidationGroups.PatternCheckGroup.class})
public interface ValidationSequence {
}
