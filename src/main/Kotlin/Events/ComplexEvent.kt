package Events

import Data.Complex
import tornadofx.EventBus

import tornadofx.FXEvent

class ComplexEvent(val complex: Complex = Complex(0.0,0.0)): FXEvent(EventBus.RunOn.BackgroundThread)